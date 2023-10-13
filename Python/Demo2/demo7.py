sequence = input("请输入一个包含正负数的整数序列（以空格分隔）：").split()
sequence = [int(num) for num in sequence]

positive_sequence = []
negative_sequence = []

for num in sequence:
    if num >= 0:
        positive_sequence.append(num)
    else:
        negative_sequence.append(num)

positive_sequence.sort()
negative_sequence.sort(reverse=True)

sorted_sequence = positive_sequence + negative_sequence
count = len(sorted_sequence)
total_sum = sum(sorted_sequence)

print("排序结果：", sorted_sequence)
print("序列个数：", count)
print("序列的和：", total_sum)

