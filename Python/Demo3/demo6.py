def swap_key_value(dictionary):
    new_dict = {}
    for key, value in dictionary.items():
        new_dict[value] = key
    return new_dict

original_dict = {"a": 1, "b": 2, "c": 3}
swapped_dict = swap_key_value(original_dict)
print(swapped_dict)
