import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.model_selection import train_test_split
from sklearn.metrics import mean_squared_error, r2_score

# --- 1. Create a Synthetic Dataset ---
# Independent variable (X) - e.g., Hours Studied
X = np.array([1, 2, 3, 4, 5, 6, 7, 8, 9, 10]).reshape(-1, 1)
# Dependent variable (y) - e.g., Exam Score (with some noise)
y = np.array([80, 85, 90, 95, 100, 105, 110, 115, 120, 125]) + np.random.normal(0, 3, size=X.shape[0])

# --- 2. Split the data ---
# Splitting into training and testing sets (80% train, 20% test)
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# --- 3. Initialize and Train the Model ---
# Initialize the Linear Regression model
model = LinearRegression()
# Train the model using the training data
model.fit(X_train, y_train)

# --- 4. Make Predictions and Evaluate ---
# Make predictions on the test set
y_pred = model.predict(X_test)

# Calculate model performance metrics
mse = mean_squared_error(y_test, y_pred)
r2 = r2_score(y_test, y_pred)

print("--- Linear Regression Results ---")
print(f"Intercept (c): {model.intercept_:.2f}")
print(f"Coefficient/Slope (m): {model.coef_[0]:.2f}")
print(f"Mean Squared Error (MSE): {mse:.2f}")
print(f"R-squared Score (R2): {r2:.2f}")
print(f"\nExample Prediction (12 hours studied): {model.predict([[12]])[0]:.2f}")

# Optional: To visualize the result (requires matplotlib, which is generally part of ML environments)
# import matplotlib.pyplot as plt
# plt.scatter(X, y, color='blue', label='Actual Data')
# plt.plot(X, model.predict(X), color='red', label='Regression Line')
# plt.xlabel('Hours Studied')
# plt.ylabel('Exam Score')
# plt.title('Linear Regression Model')
# plt.legend()
# plt.show()