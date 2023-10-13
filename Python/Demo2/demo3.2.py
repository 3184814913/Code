import math

num = int(input("请输入一个整数："))

if num % 2 == 1:
    result = math.sqrt(num)
else:
    result = math.pow(num,1/3)

print("结果为：", result)