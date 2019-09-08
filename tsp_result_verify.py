# -*- coding: utf-8 -*-
"""
Created on Thu Jan 28 09:27:24 2016

@author: yiwzhong
"""
import numpy as np
import matplotlib.pyplot as plt

def plot_tour(solution, city_pos):
    city_number = len(solution)
    x = np.zeros(city_number+1)
    y = np.zeros(city_number+1)
    
    for i, city in enumerate(solution):
        x[i] = city_pos[int(city),0]
        y[i] = city_pos[int(city),1]

    city = solution[0]
    x[-1] = city_pos[int(city),0]
    y[-1] = city_pos[int(city),1]
    plt.plot(x, y, linewidth = 1)
    plt.scatter(x, y, marker='+', linewidths = 1)
    
##    font = {'family': 'serif',
##        'color':  'black',
##        'weight': 'normal',
##        'size': 7,
##        }
##    for i, city in enumerate(solution):
##        x[i] = city_pos[int(city),0]
##        y[i] = city_pos[int(city),1]
##        plt.text(x[i],y[i],str(int(city)), fontdict=font)
        
    plt.show()
    
    
if __name__ == "__main__":
    #read data from file
    instance = "./Results/01dsj1000.txt"
    #instance = "./Results/35lin318.txt"
    file_name = instance + "-position.csv"
    position = np.genfromtxt(file_name, delimiter=",")
    print(position)
    #print(position[0,1])
    file_name = instance + "-tour.csv"
    solution = np.genfromtxt(file_name, delimiter=',')
    print(solution)
    #draw solution figure
    plot_tour(solution, position)
