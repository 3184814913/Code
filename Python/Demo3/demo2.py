for i in range(100, 10001):
    temp = i
    sum = 0
    while temp:
        digit = temp % 10
        sum += digit ** 3
        temp //= 10
    if sum == i:
        print(i)
