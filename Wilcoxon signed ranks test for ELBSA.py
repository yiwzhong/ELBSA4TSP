import numpy as np
import scipy as sp
import scipy.stats as s


#ELBSA random seletion constant vs. sequence selection constant
data = [-0.013,0.049,-0.054,0.031,0.102,-0.042,-0.014,-0.04,0.016,-0.017,
        -0.027,0.019,0.206,0.089,0.017,0.075,-0.002,0.042,0.014,0.07,0.05,
        -0.055,0.041,0.055,0.037,0.04,0.023,0.037,0.014,0.032,0.025,0.049,0.017]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA random seletion vs. sequence selection: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA  random seletion constant vs.sequence seletion mathematic 0.375
data = [0.032,0.069,-0.013,0.105,0.11,-0.064,0.12,-0.001,0.039,-0.172,-0.011,
        0.026,0.246,0.067,0.082,0.131,0.005,0.103,-0.051,0.131,0.111,-0.122,
        0.104,0.159,0.049,-0.042,0.082,0.078,0.098,0.126,0.104,0.171,0.002]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA random seletion constant vs. mathematic 0.375: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA sequence seletion constant vs.sequence seletion mathematic 0.375
data = [0.045,0.02,0.041,0.074,0.008,-0.022,0.134,0.039,0.023,-0.155,0.016,
        0.007,0.04,-0.022,0.065,0.056,0.007,0.061,-0.065,0.061,0.061,-0.067,
        0.063,0.104,0.012,-0.082,0.059,0.041,0.084,0.094,0.079,0.122,-0.015]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA sequence seletion constant vs. mathematic 0.375: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))



#---------------------------------------------------------
#ELBSA vs. LBSA
data = [0.231,0.11,0.046,0.17,0.116,0.229,0.136,0.116,0.077,-0.064,
        -0.058,0.113,0.071,0.084,0.233,0.255,0.016,0.236,-0.286,
        0.204,0.178,-0.02,0.466,0.562,0.599,0.163,0.721,0.624,0.573,
        0.592,0.569,0.282,1.76]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. LBSA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. ASA-GS
data = [1.425,1.099,1.602,0.871,1.95,2.202,1.775,0.428,1.898,1.743,2.527,
        2.648,3.214,2.597,2.771,3.66,8.172]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. ASA-GS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. SOS-SA
data = [0.475,0.659,-0.288,0.231,-0.78,2.132,-0.355,-0.172,0.788,0.723,0.877,
        1.078,6.164,0.817,1.221,1.46,1.392]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. SOS-SA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. AHSA-TS
data = [0.412,0.225,0.508,0.991,0.317,1.365,0.976,0.874,0.755,1.252,
        1.023,-0.146,0.756,0.486,0.344,0.941,1.634,0.947,1.119,1.303]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. AHSA-TS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. D-CLPSO
data = [0.174,0.313,0.073,0.236,0.056,-0.067,0.008,0.369,0.268,0.2,
        0.233,-0.246,0.319,0.401,0.438,0.231,0.159,0.527,0.426,0.476]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. D-CLPSO: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. HBMO
data = [-0.292,-0.585,-0.363,-0.208,-0.421,-0.572,-0.195,-0.354,-0.409,-0.384,-0.37,
        -0.189,-0.328,-0.221,-0.52,-0.499,-0.107,-0.364,-0.603,-0.697,-0.595,-1.202,
        -0.434,-0.917,-0.9613,-1.2594,-0.845,-0.852,-0.902,-0.808,-0.636,-1.359,-1.246]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. HBMO: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. SOM
data = [6.156,4.194,4.757,5.647,7.076,9.088,9.805,9.096,4.192,3.925,4.634,17.249,
        9.15,6.27,9.132,9.024,19.002,9.676,1.089,6.317,7.283,14.558,4.836,12.011,
        12.046,8.922,10.547,6.681,5.278,5.137,5.114,11.691,9.484]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. SOM: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. DPIO
data = [0.084,-0.076,0.011,0.114,-0.032,0.096,0.118,0.054,0.101,0.024,0.002,
        -0.033,-0.081,0.044,0.013,0.172,-0.003,0.084,0.207,-0.111,0.027,-0.052,
        0.177,0.076,0.067,0.173,0.119,0.229,0.149,0.171,0.163,0.187,-0.078]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. DPIO: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#--------------------------------------------------------
#ELBSA vs. MSA-IBS
data = [0.355,0.649,-0.178,0.271,-0.07,0.172,0.505,-0.212,0.788,
        0.723,0.867,1.238,1.134,0.987,1.021,1.46,1.442]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. MSA-IBS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. SSA--ok
data = [-0.057,0,-0.044,0.002,-0.158,0.137,0.015,0.019,0.158,-0.752,0.071,0.065,
        0.108,0.074,0.581,0.511,0.313,0.8,0.522,0.823,0.721,0.226,0.657,
        0.737,0.92,0.7]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. SSA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. MAS--ok
data = [1.624,-0.524,0.191,0.444,-0.182,0.107,-0.148,0.166,0.816,0.624,-0.053,
        0.147,-0.094,0.237,0.756,-0.362,0.366,0.045,-0.25,-0.404,-0.717,0.831,
        3.781,3.736,3.236]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. MAS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. GA-PSO-ACO (TSHACO)--ok
data = [1.363,0.99,0.603,1.907,1.523,1.53,0.183,1.043,0.42,0.258,
        1.346,5.574,2.518,1.135,1.731,1.367,1.29,2.398,1.64,3.459,
        2.676,2.658,4.56,4.696,6.378,8.111]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. GA-PSO-ACO(TSHACO): R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. ACE--ok
data = [0.724,0.05,0.612,0.696,-0.501,0.01,0.12,-0.474,1.884,0.127,0.465,
        0.641,0.993,1.261,1.415]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. ACE: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. TSACO--ok
data = [0.01,0.252,0.801,0.276,0.367,0.884,1.075,1.957,1.672]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. TSACO: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. ESACO--ok
data = [-0.405,-0.014,-0.984,-0.297,-0.362,-0.68,-0.173,0.123,0.315,0.217,0.341]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. ESACO: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. HMMA--ok
data = [1.262,1.976,3.374,2.976,4.949,3.187,6.889,7.776,9.839,14.036,15.814,16.852]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. HMMA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. PCGA--ok
data = [0.94,1.13,1.074,1.2,2.737,1.265,4.561,4.208,5.344,5.097,6.113,
        6.655,6.281,9.064]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. PCGA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. GSAACS-PSO(HGA)--ok
data = [0.965,0.816,1.546,0.203,0.19,2.273,1.408,1.205,1.193,1.931,2.146,
        2.895,3.336,5.675,5.17]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. GSAACS-PSO(HGA): R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. Set-PSO-g
data = [1.40,1.90,2.86,3.47,2.27,3.05,1.31,3.73,2.30]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. Set-PSO-g: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. Set-PSO-i
data = [-0.08,0.16,-0.03,-0.15,-0.16,0.00,-0.47,-0.24,-0.24]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. Set-PSO-i: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. BCO
data = [0.476,0.343,0.138,0.309,0.349,0.248,0.575,0.304,-0.024,0.897,2.717,
        2.626,0.358,2.755,1.856,3.762]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. BCO: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. HDABC
data = [0.125,0.239,0.392,0.171,-0.01,0.222,0.315,-0.372,0.358,
        0.393,0.837,0.228,0.644,0.687,0.531,0.2,0.782]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. HDABC: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))



#ELBSA vs. IDCS
data = [-0.036,-0.003,0.24,-0.01,-0.007,0.438,0.07,0.01,0.586,0,0.636,0.24,
        0.432,0.534,1.593,-0.023,0.565,2.465,3.229,3.114,4.362]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. IDCS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. DCS30--ok
data = [0.275,0.849,0.889,0.408,0.054,0.977,0.651,-0.045,0.198,-0.515,
        0.788,0.548,0.494,1.295,0.268,1.494,0.456,0.27,1.009,1.999,
        1.892,2.124,0.223,1.492,1.819,2.883,2.553,2.812,1.864,2.311]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. DCS30: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. DBA
data = [0.094,0.009,0.23,-0.01,0.093,0.388,0.04,0,0.226,0.02,0.486,0.06,0.222,
        0.204,1.143,-0.243,0.305,1.685,2.109,2.254,2.512]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. DBA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. IDBA
data = [2.738,1.063,0.638,2.67,0.564,1.335,3.599,3.047,7.39,5.344]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. IDBA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. EHS
data = [0.554,0.616,0.718,0.516,1.335,1.08,0.43,-0.321,0.443,0.436,
        1.006,2.242,2.001,1.158,0.964,1.121,1.984]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. EHS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. Immune
data = [1.505,0,0.295,0.587,0.299,0.95,0.082,1.035,1.277,2.409,3.56,3.456,
        3.621,2.965]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. Immune: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))


#ELBSA vs. Weed--ok
data = [0.4347,0.94,0.8175,0.759,0.3149,0.4031,3.3289,0.2158,0.3779,
        2.3939,2.0331,2.6023,2.8982]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. Weed: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. ABA(ABO)
data = [1.725,0.346,3.206,0.773,0.51,0.683,2.568,2.615,1.123,
        0.231,0.316,0.655,2.896,-0.195,0.31]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. ABA(ABO): R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. DSOS
data = [3.392,0.252,0.671,0.93,0.464,1.493,7.46,4.39,2.159,4.834,3.155,6.874,11.817]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. DSOS: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))

#ELBSA vs. DMRSA--ok
data = [0.285,-0.014,0.146,-0.047,0,0.433,0.168,0.185,0.033,0.381,2.426,
        3.025,1.086,1.285,2.89]
n = len(data)
print(n)
r, p = s.wilcoxon(data)
print('ELBSA vs. DMRSA: R+=', n*(n-1)/2-r, 'R-=', r, 'p-value=',format(p,'.2e'))






