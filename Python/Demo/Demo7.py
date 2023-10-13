import random

num = random.randint(100, 999)  # 随机生成一个3位整数
new_num = int(str(num)[0] + '0' + str(num)[2])  # 将十位变为0
print(new_num)
