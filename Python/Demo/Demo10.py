import random

numbers = tuple(random.randint(1, 100) for _ in range(100))

number_count = {}
for number in numbers:
    if number in number_count:
        number_count[number] += 1
    else:
        number_count[number] = 1

print("每个数出现的次数：")
for number, count in number_count.items():
    print(f"{number}: {count}次")
