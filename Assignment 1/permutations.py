
def find_factorial(num):
    fact=1
    for i in range(1,num+1):
        fact *=i
    return fact

def find_perm(n,r):
    return find_factorial(n)/find_factorial(n-r)

def find_comb(n,r): 
    return find_perm(n,r)/find_factorial(r)

def use_formulas(n,r):
    # if(n<r):
    #     print(f"In finding the combinations and permutations, you can't allow n={n} to be less than r={r}");
    #     return
    
    permutation=find_perm(n,r)
    combination=find_comb(n,r)
    print(f"The permutations of {n} and {r} is {permutation}, while the combination of the numbers is {combination}")

n=int(input("Enter the value of n: "))
r=int(input("Enter the value of r: "))
use_formulas(n,r)
