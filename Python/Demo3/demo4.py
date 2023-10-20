def is_perfect_number(num):
    sum = 1
    for i in range(2, num // 2 + 1):
        if num % i == 0:
            sum += i
    return sum == num

perfect_numbers = [i for i in range(1, 10001) if is_perfect_number(i)]
print(perfect_numbers)
