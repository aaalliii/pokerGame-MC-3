package pokerSmth;


import java.util.*;

public class CombinationResolver {
    private static final Map<String, Integer> cardValueMap = Map.ofEntries(
            Map.entry("A", 14), Map.entry("K", 13), Map.entry("Q", 12), Map.entry("J", 11),
            Map.entry("10", 10), Map.entry("9", 9), Map.entry("8", 8), Map.entry("7", 7),
            Map.entry("6", 6), Map.entry("5", 5), Map.entry("4", 4), Map.entry("3", 3),
            Map.entry("2", 2), Map.entry("1", 1)
    );

    public static Combination resolve(List<Card> tableCards, List<Card> playerCards) {
        List<Card> allCards = new ArrayList<>(tableCards);
        allCards.addAll(playerCards);

        if (isStraightFlush(allCards)) {
            return Combination.STRAIGHT_FLUSH;
        }
        if (isFourOfAKind(allCards)) {
            return Combination.FOUR_OF_A_KIND;
        }
        if (isFullHouse(allCards)) {
            return Combination.FULL_HOUSE;
        }
        if (isFlush(allCards)) {
            return Combination.FLUSH;
        }
        if (isStraight(allCards)) {
            return Combination.STRAIGHT;
        }
        if (isThreeOfAKind(allCards)) {
            return Combination.THREE_OF_A_KIND;
        }
        if (isTwoPair(allCards)) {
            return Combination.TWO_PAIR;
        }
        if (isPair(allCards)) {
            return Combination.PAIR;
        }
        return Combination.HIGH_CARD;
    }

    private static boolean isFlush(List<Card> allCards) {
        Map<Suit, Integer> suitCount = new HashMap<>();
        for (Card card : allCards) {
            suitCount.merge(card.cardSuit, 1, Integer::sum);
        }
        return suitCount.containsValue(5);
    }

    private static boolean isStraight(List<Card> allCards) {
        Set<Integer> values = new HashSet<>();
        for (Card card : allCards) {
            values.add(cardValueMap.get(card.cardName));
        }

        List<Integer> sortedValues = new ArrayList<>(values);
        Collections.sort(sortedValues);

        if (sortedValues.size() < 5) return false;

        for (int i = 0; i <= sortedValues.size() - 5; i++) {
            boolean isStraight = true;
            for (int j = 0; j < 4; j++) {
                if (sortedValues.get(i + j) + 1 != sortedValues.get(i + j + 1)) {
                    isStraight = false;
                    break;
                }
            }
            if (isStraight) return true;
        }

        return false;
    }

    private static boolean isStraightFlush(List<Card> allCards) {
        return isFlush(allCards) && isStraight(allCards);
    }

    private static boolean isFourOfAKind(List<Card> allCards) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Card card : allCards) {
            countMap.merge(card.cardName, 1, Integer::sum);
        }
        return countMap.containsValue(4);
    }

    private static boolean isFullHouse(List<Card> allCards) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Card card : allCards) {
            countMap.merge(card.cardName, 1, Integer::sum);
        }

        boolean hasThreeOfAKind = false;
        boolean hasPair = false;

        for (int count : countMap.values()) {
            if (count == 3) {
                hasThreeOfAKind = true;
            }
            if (count == 2) {
                hasPair = true;
            }
        }

        return hasThreeOfAKind && hasPair;
    }

    private static boolean isThreeOfAKind(List<Card> allCards) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Card card : allCards) {
            countMap.merge(card.cardName, 1, Integer::sum);
        }
        return countMap.containsValue(3);
    }

    private static boolean isTwoPair(List<Card> allCards) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Card card : allCards) {
            countMap.merge(card.cardName, 1, Integer::sum);
        }

        int pairCount = 0;
        for (int count : countMap.values()) {
            if (count == 2) {
                pairCount++;
            }
        }

        return pairCount == 2;
    }

    private static boolean isPair(List<Card> allCards) {
        Map<String, Integer> countMap = new HashMap<>();
        for (Card card : allCards) {
            countMap.merge(card.cardName, 1, Integer::sum);
        }
        return countMap.containsValue(2);
    }

    public static int compareHands(List<Card> player1Cards, List<Card> player2Cards,
                                   Combination player1Combination, Combination player2Combination) {
        int combinationComparison = Integer.compare(player1Combination.ordinal(), player2Combination.ordinal());

        if (combinationComparison != 0) {
            return combinationComparison;
        }

        if (player1Combination == Combination.HIGH_CARD) {
            return compareHighCard(player1Cards, player2Cards);
        }
        return 0;
    }

    private static int compareHighCard(List<Card> player1Cards, List<Card> player2Cards) {
        List<Integer> player1Values = getSortedCardValues(player1Cards);
        List<Integer> player2Values = getSortedCardValues(player2Cards);

        for (int i = 0; i < 5; i++) {
            int comparison = Integer.compare(player1Values.get(i), player2Values.get(i));
            if (comparison != 0) {
                return comparison;
            }
        }

        return 0;
    }

    private static List<Integer> getSortedCardValues(List<Card> cards) {
        List<Integer> values = new ArrayList<>();
        for (Card card : cards) {
            values.add(cardValueMap.get(card.cardName));
        }
        Collections.sort(values, Collections.reverseOrder());
        return values;
    }
}