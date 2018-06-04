//
//

#include <stdio.h>
#include <stdlib.h>
#include "queue.h"


// add item to the rear of the queue
ItemType * enqueue(Queue *queue, ItemType *item) {

    // create a new node
    ListNode *thisNode = (ListNode *)malloc(sizeof(ListNode));

    // returns null if empty
    if (thisNode == NULL) {
        return NULL;
    }
    //assign node to current item
    (*thisNode).item = item;

    // make nodes next point to null
    (*thisNode).next = NULL;

    // if first node then make it both front and end of queue
    if (queueSize(*queue) == 0) {
        (*queue).front = thisNode;
    }
    // if not first add to end of queue
    else {
        (*((*queue).rear)).next = thisNode;
    }
    (*queue).rear = thisNode;
    (*queue).size++;

    return item;
}



// remove item from front of the queue
ItemType * dequeue(Queue *queue) {

    // assign node to front of the queue
    ListNode *thisNode = (*queue).front;

    // if queue is not empty remove the item from the front
    if (thisNode != NULL) {
        (*queue).front = (*thisNode).next;
        ItemType *temp = (*thisNode).item;
        free(thisNode);
        (*queue).size--;
        return temp;
    }
    //if empty return null
    else {
        return NULL;
    }
}
// returns the size of the queue
int queueSize(const Queue queue) {
    return queue.size;
}

// prints out the queue in order
void printQueue(const Queue queue, FILE *stream) {

    ListNode *front = queue.front;

    int size = queueSize(queue);
    for (int i = 0; i < size; i++) {
        fprintf(stream, "%d\n", *front->item);
        front = (*front).next;
    }
}

