import numpy as np
from scipy.stats import trim_mean, mode

data = np.array([10, 12, 15, 15, 16, 18, 20, 22, 25, 100])
print("\n--- Measures of Central Tendency Results ---")
print(f"Dataset: {data}")

data_mean = np.mean(data)
print(f"1. Mean (Average): {data_mean:.2f}")

data_median = np.median(data)
print(f"2. Median (Middle Value): {data_median:.2f}")

data_mode = mode(data)
print(f"3. Mode (Most Frequent Value): {data_mode.mode} (Count: {data_mode.count})")


trim_proportion = 0.1
data_trimmed_mean = trim_mean(data, trim_proportion)

print(f"4. Trimmed Mean (10% trim): {data_trimmed_mean:.2f}")