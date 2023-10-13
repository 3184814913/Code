import math

num = int(input("请输入一个整数："))

result = math.sqrt(num) if num % 2 == 1 else num ** (1/3)

print("结果为：", result)
