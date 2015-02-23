"""
Assignment 1 for CISC 235, Winter 2015

Written by: Anastasiya Tarnouskaya (10092484)

I confirm that this submission is my own work and is consistent
with the Queen's regulations on Academic Integrity
"""
import random
import time

ARRAY_SIZES = [250, 500, 1000, 2000, 4000]

def main():

    print " " 
    """Experiment 1:"""
    print "Experiment 1"
    print "------------"

    for ARRAY_SIZE in ARRAY_SIZES:
        print "n = ", ARRAY_SIZE
        num_searches = ARRAY_SIZE/2
        while num_searches <= (ARRAY_SIZE + ARRAY_SIZE/2):
                print "     k = ", num_searches
                #generates a list of n randomly selected integers
                lis = arrayGen(ARRAY_SIZE*10)

                #sorts integers in ascending order 
                quicksort(lis)

                #generating a list of k (NUM_SEARCHES) search values consisting
                #of values present in the array
                vals2search = searchVals(num_searches, lis)

                #times how long a binary search takes 
                start = time.clock()
                for val in vals2search:
                    bin_search(lis, 0, ARRAY_SIZE - 1, val)

                end = time.clock()
                print "     The binary search for Experiment 1 took", format((end - start)*1000, '.2f'), "milliseconds"

                #times how long a trinary search takes 
                start2 = time.clock()
                for val in vals2search:
                    trin_search(lis, 0, ARRAY_SIZE - 1, val)

                end2 = time.clock()
                print "     The trinary search for Experiment 1 took", format((end2 - start2)*1000, '.2f'), "milliseconds"
                print " "
                num_searches += ARRAY_SIZE/2
        
    ###############################################################

    INDEX = 0
    print "========================================================================="
    """Experiment 2: """
    print "Experiment 2"
    print "------------"
    
    for ARRAY_SIZE in ARRAY_SIZES:
        print "n = ", ARRAY_SIZE
        num_searches = ARRAY_SIZE/2
        while num_searches <= (ARRAY_SIZE + ARRAY_SIZE/2):
            print "     k = ", num_searches
            #generates a list of n randomly selected ODD integers
            lis = arrayGen(ARRAY_SIZE*10)
            for val in lis:
                val = (val*2)+1

            #sorts integers in ascending order 
            quicksort(lis)

            #generating a list of k (NUM_SEARCHES) search values consisting
            #of EVEN values 
            vals2search = searchVals(num_searches, lis)

            for val in vals2search:
                val = (val*2)

            #times how long a binary search takes 
            start = time.clock()
            for val in vals2search:
                bin_search(lis, 0, ARRAY_SIZE - 1, val)

            end = time.clock()
            print "     The binary search for Experiment 2 took", format((end - start)*1000, '.2f'), "milliseconds"

            #times how long a trinary search takes 
            start2 = time.clock()
            for val in vals2search:
                trin_search(lis, 0, ARRAY_SIZE - 1, val)

            end2 = time.clock()
            print "     The trinary search for Experiment 2 took", format((end2 - start2)*1000, '.2f'), "milliseconds"
            print " "
            num_searches += ARRAY_SIZE/2


            """end main"""
    ###############################################################
    
#_______________________HELPER__FUNCTIONS__________________________#
            
"""Generates a random array of Integers of length numInts"""
def arrayGen(numInts):
    lis = []
    n = 0
    for i in range(numInts):
        lis.append(random.randint(0,numInts))
    return lis

"""quick sort algorithm"""
def quicksort(lis, low=0, high=None):
    if high == None:
        high = len(lis)-1
    if low >= high:
        return 
    mid = (low+high)//2
    i = low
    j = low
    lis[low],lis[mid] = lis[mid],lis[low]
    while j < high:
        j += 1
        if lis[j] < lis[low]:
            i += 1
            lis[i],lis[j] = lis[j],lis[i]
    lis[low],lis[i] = lis[i],lis[low]

    quicksort(lis,i+1,high)
    quicksort(lis,low,i)

"""returns an array of integers to search for"""
def searchVals(k, array):
    arraySize = len(array)
    arrayVals = []
    valList = []

    #determines all integers in the array
    for i in range (0,arraySize-1):
        if array[i] not in arrayVals:
            arrayVals.append(array[i])

    #generates a set of k search values
    i = 0
    while i < k: 
        toAppend = random.choice(arrayVals)
        #if toAppend not in valList:
        valList.append(toAppend)
        i += 1
           
    return valList

"""binary search provided for the assignment"""
def bin_search(A, first, last, target):
    # returns index of target in A, if present
    # returns -1 if target is not present in A
    
    if first > last:
        return -1
    else:
        mid = (first+last)/2
    if A[mid] == target:
        return mid
    elif A[mid] > target:
        return bin_search(A,first,mid-1,target)
    else:
        return bin_search(A,mid+1,last,target)

"""trinary search provided for the assignment"""
def trin_search(A,first,last,target):
# returns index of target in A, if present
# returns -1 if target is not present in A
    if first > last:
        return -1
    else:
        one_third = first + (last-first)/3
        two_thirds = first + 2*(last-first)/3
        if A[one_third] == target:
            return one_third
        elif A[one_third] > target:
            # search the left-hand third
            return trin_search(A,first,one_third-1,target)
        elif A[two_thirds] == target:
            return two_thirds
        elif A[two_thirds] > target:
            # search the middle third
            return trin_search(A,one_third+1,two_thirds-1,target)
        else:
            # search the right-hand third
            return trin_search(A,two_thirds+1,last,target)


#runs the program for 
main()
