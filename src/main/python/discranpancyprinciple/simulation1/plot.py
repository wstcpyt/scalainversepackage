import numpy as np

y = np.loadtxt('simulation1/simulation1.txt')
yExact = np.loadtxt("xExact.txt")
import pylab as plt
x = np.arange(len(y))
plt.plot(x, yExact)
plt.plot(x, y, "*")
plt.show()