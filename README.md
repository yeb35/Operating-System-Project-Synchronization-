# Operating-System-Project-Synchronization-
o find a solution of to the readers-writers synchronization problem,  which applies to a data set such as a file shared between more than one process at a time

oTo manage synchronization between different reader and writer processes, the readerwriters problem is used so that there are no issues with the data sets, i.e. no inconsistency is 
created.
There are 4 rules that should be considered in the reader and writer problem. These; If two 
or more than two readers want to access the file at the same point in time there will be no 
problem. However, in other situations like when two writers or one reader and one writer 
wants to access the file at the same point of time, there may occur some problems, hence 
the task is to design the code in such a manner that if one reader is reading then no writer is 
allowed to update at the same point of time, similarly, if one writer is writing no reader is 
allowed to read the file at that point of time and if one writer is updating a file other writers 
should not be allowed to update the file at the same point of time. However, multiple 
readers can access the object at the same time.
