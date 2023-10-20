def calculate_e(n):
    e = 1
    factorial = 1
    for i in range(1, n + 1):
        factorial *= i
        e += 1 / factorial
    return e

n = int(input("请输入一个整数："))
result = calculate_e(n)
print("自然对数的底数e的近似值为：", result)
