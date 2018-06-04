#include <stdio.h>
#include "messageService.h"

static LinkType blocksHead; // Circular queue index for first available block
static LinkType blocksTail; // Circular queue index for last available block
static LinkType blocksFree[N_MESSAGE_BLOCKS];  // Free list is a circular queue
// For each block, the link to the next block (-1 indicates no next block)
static LinkType links[N_MESSAGE_BLOCKS];
static int blocksAvailable; // Current number of free blocks
static char messageBlocksInitialized = 0;

int main() {
    printf("Hello, World!\n");
    return 0;
}

void initializeMessageBlocks(void) {
    for (int k = 0; k < N_MESSAGE_BLOCKS; k++) {
        blocksFree[k] = k;
    }
    blocksHead = 0;
    blocksTail = N_MESSAGE_BLOCKS - 1;
    blocksAvailable = N_MESSAGE_BLOCKS;
    messageBlocksInitialized = 1;
}


Message createMessage(const char *myMessage, const int nBytes) {
    if (!messageBlocksInitialized) {
        initializeMessageBlocks();
    }

    Message retVal;
    retVal.nBytes = nBytes;

    // Division by a power of 2 is simply a shift
    int blocksNeeded = nBytes >> (MESSAGE_BLOCK_BITS);
    // Need one more if there is a remainder
    if (nBytes & MESSAGE_BLOCK_MASK) {
        ++blocksNeeded;
    }

    if (blocksNeeded > blocksAvailable) {
        retVal.nBytes = -1;   // Mark as invalid Message
        return retVal;
    }

    LinkType firstBlock = getBlock();
    LinkType thisBlock = firstBlock;

    retVal.firstBlock = firstBlock;

    DPRINT("Need %d. Using blocks: ", blocksNeeded);

// Go into a loop to copy data into each block and link the blocks

}