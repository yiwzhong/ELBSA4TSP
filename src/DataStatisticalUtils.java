
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class DataStatisticalUtils {
    /**
     * 
     * @param arr
     * @return
     */
    public static double getSum(double[] arr) {
        double sum = 0;
        for (double num : arr) {
            sum += num;
        }
        return sum;
    }

    /**
     *
     * @param arr
     * @return
     */
    public static double getMean(double[] arr) {
        return getSum(arr) / arr.length;
    }

    /**
     * Mode
     *
     * @param arr
     * @return
     */
    public static double getMode(double[] arr) {
        Map<Double, Integer> map = new HashMap<Double, Integer>();
        for (int i = 0; i < arr.length; i++) {
            if (map.containsKey(arr[i])) {
                map.put(arr[i], map.get(arr[i]) + 1);
            } else {
                map.put(arr[i], 1);
            }
        }
        int maxCount = 0;
        double mode = -1;
        Iterator<Double> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            double num = iter.next();
            int count = map.get(num);
            if (count > maxCount) {
                maxCount = count;
                mode = num;
            }
        }
        return mode;
    }

    /**
     * Median
     *
     * @param arr
     * @return
     */
    public static double getMedian(double[] arr) {
        double[] tempArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(tempArr);
        if (tempArr.length % 2 == 0) {
            return (tempArr[tempArr.length >> 1] + tempArr[(tempArr.length >> 1) - 1]) / 2;
        } else {
            return tempArr[(tempArr.length >> 1)];
        }
    }


    /**
     * Middle range
     *
     * @param arr
     * @return
     */
    public static double getMidrange(double[] arr) {
        double max = arr[0], min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return (min + max) / 2;
    }

    /**
     * ���ķ�λ��
     *
     * @param arr
     * @return ��������ķ�λ��������
     */
    public static double[] getQuartiles(double[] arr) {
        double[] tempArr = Arrays.copyOf(arr, arr.length);
        Arrays.sort(tempArr);
        double[] quartiles = new double[3];
        // �ڶ��ķ�λ������λ����
        quartiles[1] = getMedian(tempArr);
        // �����������ķ�λ��
        if (tempArr.length % 2 == 0) {
            quartiles[0] = getMedian(Arrays.copyOfRange(tempArr, 0, tempArr.length / 2));
            quartiles[2] = getMedian(Arrays.copyOfRange(tempArr, tempArr.length / 2, tempArr.length));
        } else {
            quartiles[0] = getMedian(Arrays.copyOfRange(tempArr, 0, tempArr.length / 2));
            quartiles[2] = getMedian(Arrays.copyOfRange(tempArr, tempArr.length / 2 + 1, tempArr.length));
        }
        return quartiles;
    }

    /**
     * �󼫲�
     *
     * @param arr
     * @return
     */
    public static double getRange(double[] arr) {
        double max = arr[0], min = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        return max - min;
    }

    /**
     * ���ķ�λ������
     *
     * @param arr
     * @return
     */
    public static double getQuartilesRange(double[] arr) {
        return getRange(getQuartiles(arr));
    }

    /**
     * ��ضϾ�ֵ
     *
     * @param arr ��ֵ����
     * @param p   �ض���p������p��ֵΪ20����ض�20%����10%����10%��
     * @return
     */
    public static double getTrimmedMean(double[] arr, int p) {
        int tmp = arr.length * p / 100;
        double[] tempArr = Arrays.copyOfRange(arr, tmp, arr.length + 1 - tmp);
        return getMean(tempArr);
    }

    /**
     * �󷽲�
     *
     * @param arr
     * @return
     */
    public static double getVariance(double[] arr) {
        double variance = 0;
        double sum = 0, sum2 = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            sum2 += arr[i] * arr[i];
        }
        variance = sum2 / arr.length - (sum / arr.length) * (sum / arr.length);
        return variance;
    }

    /**
     * �����ƽ��ƫ��(AAD)
     *
     * @param arr
     * @return
     */
    public static double getAbsoluteAverageDeviation(double[] arr) {
        double sum = 0;
        double mean = getMean(arr);
        for (int i = 0; i < arr.length; i++) {
            sum += Math.abs(arr[i] - mean);
        }
        return sum / arr.length;
    }

    /**
     * ����λ������ƫ��(MAD)
     *
     * @param arr
     * @return
     */
    public static double getMedianAbsoluteDeviation(double[] arr) {
        double[] tempArr = new double[arr.length];
        double median = getMedian(arr);
        for (int i = 0; i < arr.length; i++) {
            tempArr[i] = Math.abs(arr[i] - median);
        }
        return getMedian(tempArr);
    }

    /**
     * ���׼��
     * @param arr
     * @return
     */
    public static double getStandardDevition(double[] arr) {
        double sum = 0;
        double mean = getMean(arr);
        for (int i = 0; i < arr.length; i++) {
            sum += Math.sqrt((arr[i] - mean) * (arr[i] - mean));
        }
        return (sum / (arr.length - 1));
    }


}