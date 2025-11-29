def sum_of_divisors(n):
    if n==1:
        return 0
    
    total = 1
    for i in range(2,int(n**0.5)+1): 
        if n % i == 0:  
            total += i
            other=n//i
            if other !=i:
                total+=other
    return total

def find_amicable_numbers(start, end):
    if start > end:
        start,end=end,start

    print("Amicable numbers between", start, "and", end, "are:")
    for a in range(start, end + 1):
        b = sum_of_divisors(a)
        if b > a and b <= end:      
            if sum_of_divisors(b) == a:
                print(a, "and", b)

low_number=input("Input the first number: ")
high_number=input("Enter the second number: ")

find_amicable_numbers(int(low_number),int(high_number))
