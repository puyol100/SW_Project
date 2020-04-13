import numpy as np
import matplotlib.pyplot as plt
import wave
import librosa
from scipy.fftpack import fft
import math
from pydub import AudioSegment
import sys
import audio2binary_v2

def DeTect(n, file_name):

	peaks = audio2binary_v2.a2b(file_name)
	print(peaks)
	y, sr = librosa.load(file_name);

	smooth = []
	addi = 0
	for i in range(0, len(y)):
		if i % sr == 0:
			smooth.append(addi/sr)
			addi = 0
		addi = addi + abs(y[i])

	db = librosa.amplitude_to_db(smooth, ref = 0.00002)

	max_db = round(np.max(db))
	mean_db = round(np.mean(db))

	snore_cnt = len(peaks)
	apnea_count = 0
	zero_cnt = 0

	apnea_cnt = 0
	for i in range(0, len(peaks)):
		if i == 0:
			term = peaks[i] - 0
		else:
			term = peaks[i] - peaks[i-1]

		if term >= 30 and term <= 60:
			print('start:', peaks[i-1])
			print('end:', peaks[i])
			apnea_cnt = apnea_cnt + 1

	result_arr = np.zeros(4)
	if apnea_cnt >= 15:
		result_arr[0] = 1
	else:
		result_arr[0] = 0

	result_arr[1] = max_db
	result_arr[2] = mean_db
	result_arr[3] = apnea_cnt

	print('max_db:', max_db)
	print('mean_db:', mean_db)
	print('snore count:', snore_cnt)
	print('apnea_cnt:', apnea_cnt)
	return result_arr
