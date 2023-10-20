def remove_duplicates(lst):
    return list(set(lst))

lst = [1, 2, 3, 4, 4, 5, 6, 6, 7]
unique_lst = remove_duplicates(lst)
print(unique_lst)
