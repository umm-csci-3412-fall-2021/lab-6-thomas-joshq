# Experimental results

Results for running stress test to the servers with pumpkins.jpg (48.3 kb)

| Calls | Threaded (Seconds) | Not Threaded (Seconds) |
| ------------- | ------------- | ------------- |
| 50  | 7 | 7  |
| 100  | 7  | 12  |
| 200 | 34  | 36  |
| 500 | 55  | crashes  |



Results for running stress test to the servers with flower.jpg (2.9 mb)

| Calls | Threaded (Seconds) | Not Threaded (Seconds) |
| ------------- | ------------- | ------------- |
| 1  | 18 | 22  |
| 5  | 19  | 39  |
| 10 | 33  | 67  |


From the results we can see that with a small file the times are very close, but from a large file the times are much better with a threaded server. Also can note the threaded server could handle more calls without crashing.

All tests run on a computer running Ubuntu 20.04.3 LTS with a 4 core Intel® Core™ i5-4460 CPU @ 3.20GHz