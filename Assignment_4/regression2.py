import numpy as np
from sklearn.linear_model import LinearRegression

X = np.array([1, 2, 3, 4, 5]).reshape(-1, 1)
y = np.array([300, 350, 400, 450, 500])

model = LinearRegression()
model.fit(X, y)

prediction = model.predict([[6]])
print("Predicted Salary:", prediction)
        