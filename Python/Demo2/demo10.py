import random

target_number = random.randint(1, 1000)
guess_count = 0

while True:
    guess = input("请输入一个猜测的整数(1至1000)：")

    # 检查输入是否为整数
    if not guess.isdigit():
        print("输入有误，请重试，不计入猜测次数哦！")
        continue

    guess = int(guess)
    guess_count += 1

    if guess == target_number:
        print("猜对了")
        print("此轮的猜测次数是:", guess_count)
        break
    elif guess > target_number:
        print("猜大了")
    else:
        print("猜小了")
