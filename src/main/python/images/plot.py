__author__ = 'yutongpang'
import numpy as np

x = np.loadtxt('test.txt')
print(x)

import pylab

pylab.imshow(x,cmap=pylab.gray())
pylab.show()

from scipy.linalg import toeplitz

