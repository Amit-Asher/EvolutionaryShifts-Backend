import requests
import json
import sys

# HOW TO USE

# simple example (with default parameters):
# python loadDb.py

# schema:
# python loadDb.py -f <filename> -u <url> --full

# FLAGS:
# -f: load db from a custom json file
# -d: send request to the base-url (not the endpoint!) on internet
# --full: load also arrangement details

# custom example with flags:
# python loadDb.py -f restaurantDb.json -u http://localhost:8080 --full

# default parameters:
base_url = "http://localhost:8080"
db_json_file = "restaurantDb.json"
full_load = False
flag = ''


def load_flags():
    global base_url
    global db_json_file
    global full_load
    global flag

    # load the flags
    for arg in sys.argv:
        # -f: load db from a custom json file
        if flag == '-f':
            db_json_file = arg
            flag = ''

        # -d: send request to a custom url
        if flag == '-u':
            base_url = arg
            flag = ''

        # save the flag to switch in the next iteration
        if arg in ['-f', '-u', '--full', '--help', '-h']:
            flag = arg
        
        # --full: load also arrangement details
        if flag == '--full':
            full_load = True
            flag = ''
        
        # print help and instructions
        if flag == '--help' or flag == '-h':
            print('\n***** loadDb: Auto-Generate Database Tool *****\n')
            print('HOW TO USE:')
            print('python loadDb.py -f <filename> -u <url> --full')
            print()
            print('FLAGS:')
            print('-f: load db from a custom json file')
            print('-u: send request to the base-url (not the endpoint!) on internet')
            print('--full: load also arrangement details')
            print()
            print('EXAMPLE:')
            print('python loadDb.py -f restaurantDb.json -u http://localhost:8080 --full')
            exit(1)


load_flags()
# load the database from the json file
with open(db_json_file) as f:
    data = json.load(f)
    payload = json.dumps(data)


# send request to load db from api
headers = {"Content-Type": "application/json"}
print("loading company db...")
cmp_url = base_url + '/dev/loadCompanyDb'
response = requests.request("POST", cmp_url, headers=headers, data=payload)
print("finish loading company db")

if full_load:
    print("loading arrangement db...")
    argmt_url = base_url + '/dev/loadArrangement'
    response = requests.request("POST", argmt_url, headers=headers, data=payload)
    print("finish loading arrangement db")

# print the database
print()
print(json.dumps(data, indent=4, sort_keys=True))
print("\nloading database completed successfully!")
