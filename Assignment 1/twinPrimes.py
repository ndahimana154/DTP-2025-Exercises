def is_prime(num):
    if num < 2:
        return False
    for i in range(2, int(num**0.5) + 1): 
        if num % i == 0:
            return False
    return True

def print_twin_primes():
    for n in range(2, 1000):
        if is_prime(n) and is_prime(n + 2):
            print(n, "and", n + 2)

print_twin_primes()
