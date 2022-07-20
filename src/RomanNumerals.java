class RomanNumerals {

    public  static int romanToArabic(String romanNum )
    {
        int value = 0;
        int lastNum =0;
        romanNum = romanNum.toUpperCase();
        for(int i = romanNum.length() - 1; i >=0; i--)
        {
            switch (romanNum.charAt(i))
            {
                case 'I':
                    value += checkLastNumber(1, lastNum);
                    lastNum = 1;
                    break;
                case 'V':
                    value += checkLastNumber(5, lastNum);
                    lastNum = 5;
                    break;
                case 'X':
                    value += checkLastNumber(10, lastNum);
                    lastNum = 10;
                    break;
                default: return -1;
            }
        }

        return value;
    }
    private static int checkLastNumber(int nowNum, int lastNum)
    {
        if(lastNum > nowNum)
            return -nowNum;
        else
            return  nowNum;
    }

    public static String arabicToRoman(int arabicNum )
    {
        final String[] ROMAN_CHAR = new String[]{"C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        final int[] ARABIC_INT = new int[]{100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder value = new StringBuilder();
        for (int i = 0; i < ARABIC_INT.length; i++ )
        {
            while (arabicNum >= ARABIC_INT[i])
            {
                arabicNum -= ARABIC_INT[i];
                value.append(ROMAN_CHAR[i]);
            }

        }

        return value.toString();
    }
}
