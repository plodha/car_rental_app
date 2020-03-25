import pymysql


# Open database connection
db = pymysql.connect("host","user","password","db" )

# Connect to the database
connection = pymysql.connect(host='localhost',
user='root',
db='car-rent',
charset='utf8',
cursorclass=pymysql.cursors.DictCursor)

# prepare a cursor object using cursor() method
cursor = db.cursor()

# execute SQL query using execute() method.
cursor.execute("SELECT VERSION()")

# Fetch a single row using fetchone() method.
data = cursor.fetchone()
print ("Database version : %s " % data)

cursor.execute("SHOW TABLES")
result = cursor.fetchall()
print(result)
for i in range(len(result)):
	print(result[i])



