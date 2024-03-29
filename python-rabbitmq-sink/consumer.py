import pika, os


# Access the CLODUAMQP_URL environment variable and parse it (fallback to localhost)
url = os.environ.get('CLOUDAMQP_URL', 'amqp://guest:guest@localhost:5672/%2f')
params = pika.URLParameters(url)
connection = pika.BlockingConnection(params)
channel = connection.channel() # start a channel
channel.queue_declare(queue='consumer') # Declare a queue

# create a function which is called on incoming messages
def callback(ch, method, properties, body):
    print("Received =%r" % body.decode())

# set up subscription on the queue
channel.basic_consume('consumer', callback, auto_ack=True)

# start consuming (blocks)
channel.start_consuming()
connection.close()