from typing import List

def is_course(cor: str) -> bool:
    return len(cor) == 9 and cor[:3].isupper() and cor[3:6].isdigit()


def get_data():
    file = open("ReferenceFiles\\SampleData.csv")

    currcourse = ""
    data = {}
    for line in file:
        linedata = line.split(sep=",")
        if is_course(linedata[0]):
            currcourse = f"{linedata[0]}"
            if currcourse not in data:
                data[currcourse] = []
            data[currcourse].append(linedata[:])
    return data


def _format_Date(dat: str):
    if dat == "Mon":
        return "Monday"
    elif dat == "Tue":
        return "Tuesday"
    elif dat == "Wed":
        return "Wednesday"
    elif dat == "Thu":
        return "Thursday"
    elif dat == "Fri":
        return "Friday"


def _formatTime(t: str):
    timesplit = t.split(sep=":")
    if timesplit[0] == "":
        return t
    if int(timesplit[0]) > 12:
        return f"{int(timesplit[0]) - 12}:{timesplit[1]}PM"
    else:
        return f"{int(timesplit[0])}:{timesplit[1]}AM"


def formatDateTime(date: str, stime: str, etime: str):
    return f"{_format_Date(date)} {_formatTime(stime)} - {_formatTime(etime)}"


# CONSTANTS
CODE = 0
SECTION = 1
DATE = 3
START_TIME, END_TIME = 4, 5
LOCATION = 6
INSTRUCTOR_FIRSTNAME, INSTRUCTOR_LASTNAME = 7, 8
DELIVERY = -1


def partitionList(lst: List[List[str]]):
    d = {}
    for sublst in lst:
        if sublst[SECTION] not in d:
            d[sublst[SECTION]] = []
        d[sublst[SECTION]].append(sublst)
    return d


def prepare_to_write(obj: List[List[str]]):
    diction = partitionList(obj)

    s = ""
    for k in diction:
        dat = diction[k][0]
        s += f"{k},{formatDateTime(dat[DATE], dat[START_TIME], dat[END_TIME])}," \
             f"{dat[LOCATION]},{dat[INSTRUCTOR_FIRSTNAME]} " \
             f"{dat[INSTRUCTOR_LASTNAME]}, {dat[DELIVERY][:-1]}, 0\n"
        if len(k) > 1:
            for item in diction[k][1:]:
                s += f",{formatDateTime(item[DATE], item[START_TIME], item[END_TIME])},{item[LOCATION]}\n"
        s += ",,,,\n"
    return s[:-1]


def main():
    dat = get_data()
    for it in dat:
        s = f"{it},Fall,Faculty of Applied Science and Engineering,,\n" \
             f"ACTIVITY,TIME,LOCATION,INSTRUCTOR,DELIVERY METHOD\n" \
             f"{prepare_to_write(dat[it])}\n"
        output = open(f"{it[:-1]}.csv", 'w')
        output.write(s)
        output.close()


if __name__ == '__main__':
    main()
