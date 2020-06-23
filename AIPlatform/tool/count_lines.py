import os

cwd = os.getcwd()


def count(cwd):
    lines_sum = 0
    file_names = os.listdir(cwd)
    for file_name in file_names:
        if file_name in [".idea", "venv", "static"] or file_name.count("__"):
            continue
        path = cwd + "/" + file_name
        if os.path.isfile(path):
            print(file_name)
            lines_num = open(path, "r").read().count("\n")
            print(lines_num)
            lines_sum += lines_num
        if os.path.isdir(path):
            lines_sum += count(path)
    return lines_sum


print(count(cwd))
