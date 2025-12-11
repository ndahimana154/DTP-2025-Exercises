package com.example.practicalapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import java.util.*

class FirestoreActivity : AppCompatActivity() {

    private val db = FirebaseFirestore.getInstance()
    private val storage = FirebaseStorage.getInstance().reference

    private var selectedImageUri: Uri? = null

    private val pickImage = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            selectedImageUri = it
            findViewById<ImageView>(R.id.ivSelected).setImageURI(it)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_firestore)

        val etName = findViewById<EditText>(R.id.etName)
        val btnPick = findViewById<Button>(R.id.btnPickImage)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val rv = findViewById<RecyclerView>(R.id.rvUsers)

        btnPick.setOnClickListener {
            pickImage.launch("image/*")
        }

        val adapter = UserAdapter(mutableListOf(), object : UserAdapter.Listener {
            override fun onEdit(user: User) {
                etName.setText(user.name)
                // store id somewhere (for simplicity attach to view tag)
                findViewById<Button>(R.id.btnAdd).tag = user.id
            }

            override fun onDelete(user: User) {
                // delete doc and storage image if exists
                db.collection("users").document(user.id).delete()
                    .addOnSuccessListener {
                        Toast.makeText(this@FirestoreActivity, "Deleted", Toast.LENGTH_SHORT).show()
                        // try delete storage
                        if (user.imagePath.isNotBlank()) {
                            storage.child(user.imagePath).delete()
                        }
                        loadUsers()
                    }
            }
        })
        rv.layoutManager = LinearLayoutManager(this)
        rv.adapter = adapter

        btnAdd.setOnClickListener {
            val name = etName.text.toString()
            val editingId = it.tag as? String
            if (name.isBlank()) return@setOnClickListener

            if (selectedImageUri != null) {
                // upload image first
                val path = "images/${UUID.randomUUID()}.jpg"
                val ref = storage.child(path)
                ref.putFile(selectedImageUri!!).addOnSuccessListener {
                    ref.downloadUrl.addOnSuccessListener { url ->
                        if (editingId == null) {
                            val id = UUID.randomUUID().toString()
                            val user = User(id, name, url.toString(), path)
                            db.collection("users").document(id).set(user).addOnSuccessListener {
                                etName.text.clear()
                                selectedImageUri = null
                                findViewById<ImageView>(R.id.ivSelected).setImageResource(0)
                                loadUsers()
                            }
                        } else {
                            // update doc
                            val data = mapOf("name" to name, "imageUrl" to url.toString(), "imagePath" to path)
                            db.collection("users").document(editingId).update(data).addOnSuccessListener {
                                etName.text.clear()
                                it.tag = null
                                selectedImageUri = null
                                findViewById<ImageView>(R.id.ivSelected).setImageResource(0)
                                loadUsers()
                            }
                        }
                    }
                }
            } else {
                if (editingId == null) {
                    val id = UUID.randomUUID().toString()
                    val user = User(id, name, "", "")
                    db.collection("users").document(id).set(user).addOnSuccessListener {
                        etName.text.clear()
                        loadUsers()
                    }
                } else {
                    db.collection("users").document(editingId).update("name", name).addOnSuccessListener {
                        etName.text.clear()
                        it.tag = null
                        loadUsers()
                    }
                }
            }
        }

        loadUsers()
    }

    private fun loadUsers() {
        db.collection("users").get().addOnSuccessListener { snapshot ->
            val list = snapshot.documents.mapNotNull { it.toObject(User::class.java) }
            val rv = findViewById<RecyclerView>(R.id.rvUsers)
            (rv.adapter as? UserAdapter)?.update(list.toMutableList())
        }
    }
}
