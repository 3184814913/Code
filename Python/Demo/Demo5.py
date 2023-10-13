nums = list(map(int, input().split()))
sum_1 = nums[0] + nums[1]
sum_2 = nums[0] + nums[2]
sum_3 = nums[1] + nums[2]
max_sum = max(sum_1, sum_2, sum_3)
print(max_sum)
