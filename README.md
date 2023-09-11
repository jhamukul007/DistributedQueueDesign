# DistributedQueueDesign
Distributed Queue Design Machine coding or Low-level design
# Requirements
1. The queue should be in memory and should not require access to the file system. <br/>
2. There can be multiple topics in the queue. <br/>
3. A (string) message can be published on a topic by a producer/publisher and consumers/subscribers can subscribe to the topic to receive the messages.<br/>
4. There can be multiple producers and consumers.<br/>
5. A producer can publish multiple topics.<br/>
6. A consumer can listen to multiple topics.<br/>
7. The consumer should print "<consumer_id> received <message>" on receiving the message.<br/>
8. The queue system should be multi-threaded, i.e., messages can be produced or consumed in parallel by different producers/consumers.<br/>  

# Input/Output Format
1. You do not need to take input from the command-line.
2. Create 2 topics: topic1 and topic2
3. Create 2 producers: producer1, and producer2
4. Create 5 consumers: consumer1, consumer2, consumer3, consumer4, and consumer5
5. Make all 5 consumers subscribe to topic1
6. Make consumers 1, 3, and 4 subscribe to topic2
7. Make producer1 publish message "Message 1" to topic1
8. Make producer1 publish message "Message 2" to topic1
9. Make producer2 publish message "Message 3" to topic1
10. Make producer1 publish message "Message 4" to topic2
11. Make producer2 publish message "Message 5" to topic2
