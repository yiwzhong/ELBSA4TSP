import numpy as np
import scipy as sp
import scipy.stats as s
import csv


#read data from file
instance = "./Results/Performance/"
file_name = instance + "OPTIMAL.csv"
headers = []
for line in open(file_name,"r"):
    headers.append(line)

optimal = np.genfromtxt(file_name, delimiter=",")
file_name = instance + "ELBSA.csv"
elbsa = np.genfromtxt(file_name, delimiter=",")
file_name = instance + "LBSA.csv"
lbsa = np.genfromtxt(file_name, delimiter=",")
for i in range(len(elbsa)):
    y = lbsa[i]
    py = np.round((y-optimal[i][2])/optimal[i][2]*100,3)
    hs = headers[i].split(sep=",")
    print(hs[0],hs[1], sep=',', end=',')
    print(min(py), max(py), round(np.mean(py),3), np.median(py), round(np.std(py),3), sep=',', end=',')
    x = elbsa[i]
    px = np.round((x-optimal[i][2])/optimal[i][2]*100,3)
    print(min(px), max(px), round(np.mean(px),3), np.median(px), round(np.std(px),3), sep=',', end=',')
    z, p = s.ranksums(y,x)
    print(format(p,'.2e'), sep=',', end='\n')
    #print(round(z,2), format(p,'.2e'), sep=',', end='\n')



