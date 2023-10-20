fish = 1
while True:
    total = fish
    is_enough = True
    for _ in range(5):
        if (total - 1) % 5 == 0:
            total = (total - 1) // 5 * 4
        else:
            is_enough = False
            break
    if is_enough:
        print("the min number of fish is:", fish)
        break
    fish += 1
