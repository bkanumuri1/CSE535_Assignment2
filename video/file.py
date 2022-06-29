import numpy as np
import matplotlib.pyplot as plt
import pandas as pd
from dtw import *
# clear('all')
# close_('all')

# GA = xlsread('C:\Users\Sepideh\Desktop\MS\CSE 535\posenet_nodejs_setup-master\Python-Scripts\video\bluetooth0\key_points.csv')

GA = pd.read_csv('C:\\Users\\Sepideh\\Desktop\\MS\\CSE 535\\posenet_nodejs_setup-master\\Python-Scripts\\video\\bluetooth0\\key_points.csv')

rWX = GA.iloc[:,33].values
rWY = GA.iloc[:,34].values
nX = GA.iloc[:,3].values
nY = GA.iloc[:,4].values
lShX = GA.iloc[:,18].values
rShX = GA.iloc[:,21].values
hY = GA.iloc[:,37].values

##Normalize
rWXN = (rWX - nX) / (np.abs(lShX - rShX))
rWYN = (rWY - nY) / (np.abs(nY - hY))
print("1", rWXN)
print("1", rWYN)


# plt.plot(rWXN(np.arange(10,len(rWXN)+1)),rWYN(np.arange(10,len(rWYN)+1)))
plt.plot(rWXN[10:len(rWXN)+1],rWYN[10:len(rWYN)+1])
plt.draw()
plt.show()
##Secons User
# GA2 = xlsread('C:\Users\Sepideh\Desktop\MS\CSE 535\posenet_nodejs_setup-master\Python-Scripts\video\bluetooth0\key_points.csv')
# GA2 = pd.read_csv('C:\\Users\\Sepideh\\Desktop\\MS\\CSE 535\\posenet_nodejs_setup-master\\Python-Scripts\\video\\bluetooth0\\key_points.csv')


# rWX = GA2[:,34]
# rWY = GA2[:,35]
# nX = GA2[:,4]
# nY = GA2[:,5]
# lShX = GA2[:,19]
# rShX = GA2[:,22]
# hY = GA2[:,38]

# ##Normalize
# rWXN2 = (rWX - nX) / (np.abs(lShX - rShX))
# rWYN2 = (rWY - nY) / (np.abs(nY - hY))

# plt.plot(rWXN2(np.arange(11,end()+1)),rWYN2(np.arange(11,end()+1)))

GA2 = pd.read_csv('C:\\Users\\Sepideh\\Desktop\\MS\\CSE 535\\posenet_nodejs_setup-master\\Python-Scripts\\video\\bluetooth0\\key_points.csv')

rWX = GA2.iloc[:,33].values
rWY = GA2.iloc[:,34].values
nX = GA2.iloc[:,3].values
nY = GA2.iloc[:,4].values
lShX = GA2.iloc[:,18].values
rShX = GA2.iloc[:,21].values
hY = GA2.iloc[:,37].values

##Normalize
rWXN2 = (rWX - nX) / (np.abs(lShX - rShX))
rWYN2 = (rWY - nY) / (np.abs(nY - hY))



# plt.plot(rWXN(np.arange(10,len(rWXN)+1)),rWYN(np.arange(10,len(rWYN)+1)))
plt.plot(rWXN2[10:len(rWXN2)+1],rWYN2[10:len(rWYN2)+1])
plt.draw()
plt.show()


# ##New Gesture
# # GA3 = xlsread('C:\Users\Sepideh\Desktop\MS\CSE 535\posenet_nodejs_setup-master\Python-Scripts\video\bluetooth0\key_points.csv')
# GA3 = pd.read_csv('C:\\Users\\Sepideh\\Desktop\\MS\\CSE 535\\posenet_nodejs_setup-master\\Python-Scripts\\video\\bluetooth0\\key_points.csv')


# rWX = GA3[:,34]
# rWY = GA3[:,35]
# nX = GA3[:,4]
# nY = GA3[:,5]
# lShX = GA3[:,19]
# rShX = GA3[:,22]
# hY = GA3[:,38]
# ##Normalize
# rWXN3 = (rWX - nX) / (np.abs(lShX - rShX))
# rWYN3 = (rWY - nY) / (np.abs(nY - hY))

# plt.plot(rWXN3(np.arange(11,end()+1)),rWYN3(np.arange(11,end()+1)))
GA3 = pd.read_csv('C:\\Users\\Sepideh\\Desktop\\MS\\CSE 535\\posenet_nodejs_setup-master\\Python-Scripts\\video\\bluetooth0\\key_points.csv')

rWX = GA3.iloc[:,33].values
rWY = GA3.iloc[:,34].values
nX = GA3.iloc[:,3].values
nY = GA3.iloc[:,4].values
lShX = GA3.iloc[:,18].values
rShX = GA3.iloc[:,21].values
hY = GA3.iloc[:,37].values

##Normalize
rWXN3 = (rWX - nX) / (np.abs(lShX - rShX))
rWYN3 = (rWY - nY) / (np.abs(nY - hY))



# plt.plot(rWXN(np.arange(10,len(rWXN)+1)),rWYN(np.arange(10,len(rWYN)+1)))
plt.plot(rWXN3[10:len(rWXN3)+1],rWYN3[10:len(rWYN3)+1])
plt.draw()

plt.show()




# rWXN3 = np.array([[rWXN3],[np.zeros((rWXN2.shape[1-1] - rWXN3.shape[1-1],1))]])
# rWYN3 = np.array([[rWYN3],[np.zeros((rWYN2.shape[1-1] - rWYN3.shape[1-1],1))]])

# ## Trajectory
# tA1 = rWXN ** 2 + rWYN ** 2
# tA2 = rWXN2 ** 2 + rWYN2 ** 2
# tA3 = rWXN3 ** 2 + rWYN3 ** 2

# ## Dynamic Time Warping
# d12 = dtw((rWXN(np.arange(5,end()+1)) ** 2 + rWYN(np.arange(5,end()+1)) ** 2),(rWXN2(np.arange(5,end()+1)) ** 2 + rWYN2(np.arange(5,end()+1)) ** 2),'squared')
# d13 = dtw((rWYN(np.arange(5,end()+1)) ** 2 + rWYN(np.arange(5,end()+1)) ** 2),(rWYN3(np.arange(1,end()+1)) ** 2 + rWYN3(np.arange(1,end()+1)) ** 2),'squared')

# ## Cosine
# cos12 = sum(np.multiply(tA1,tA2)) / ((sum(tA1 ** 2) ** 0.5) * (sum(tA2 ** 2) ** 0.5))
# cos13 = sum(np.multiply(tA1,tA3)) / ((sum(tA1 ** 2) ** 0.5) * (sum(tA3 ** 2) ** 0.5))