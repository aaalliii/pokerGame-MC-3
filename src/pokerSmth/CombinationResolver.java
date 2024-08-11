package pokerSmth;

import java.util.*;

public class CombinationResolver {

    public static Combination resolve(List<Card> tableCards, List<Card> playerCards) {
        List<Card> allCards = new ArrayList<>(tableCards);
        allCards.addAll(playerCards);
        Collections.sort(allCards, Comparator.comparingInt(CombinationResolver::getCardValue).reversed());

        if (isStraightFlush(allCards)) return Combination.STRAIGHT_FLUSH;
        if (isFourOfAKind(allCards)) return Combination.FOUR_OF_A_KIND;
        if (isFullHouse(allCards)) return Combination.FULL_HOUSE;
        if (isFlush(allCards)) return Combination.FLUSH;
        if (isStraight(allCards)) return Combination.STRAIGHT;
        if (isThreeOfAKind(allCards)) return Combination.THREE_OF_A_KIND;
        if (isTwoPair(allCards)) return Combination.TWO_PAIR;
        return Combination.HIGH_CARD;
    }

    public static int compareHands(List<Card> tableCards, List<Card> player1Cards, List<Card> player2Cards) {
        Combination player1Combo = resolve(tableCards, player1Cards);
        Combination player2Combo = resolve(tableCards, player2Cards);

        if (player1Combo != player2Combo) {
            return Integer.compare(player1Combo.ordinal(), player2Combo.ordinal());
        } else {
            return compareByComboType(tableCards, player1Cards, player2Cards, player1Combo);
        }
    }

    private static int compareByComboType(List<Card> tableCards, List<Card> player1Cards, List<Card> player2Cards, Combination combo) {
        List<Card> allCards1 = new ArrayList<>(tableCards);
        allCards1.addAll(player1Cards);
        Collections.sort(allCards1, Comparator.comparingInt(CombinationResolver::getCardValue).reversed());

        List<Card> allCards2 = new ArrayList<>(tableCards);
        allCards2.addAll(player2Cards);
        Collections.sort(allCards2, Comparator.comparingInt(CombinationResolver::getCardValue).reversed());

        switch (combo) {
            case STRAIGHT_FLUSH:
            case FLUSH:
            case STRAIGHT:
                return compareHighCard(allCards1, allCards2);
            case FOUR_OF_A_KIND:
                return compareFourOfAKind(allCards1, allCards2);
            case FULL_HOUSE:
                return compareFullHouse(allCards1, allCards2);
            case THREE_OF_A_KIND:
                return compareThreeOfAKind(allCards1, allCards2);
            case TWO_PAIR:
                return compareTwoPair(allCards1, allCards2);
            case HIGH_CARD:
                return compareHighCard(allCards1, allCards2);
            default:
                return 0;
        }
    }

    private static int compareHighCard(List<Card> hand1, List<Card> hand2) {
        for (int i = 0; i < 5; i++) {
            int value1 = getCardValue(hand1.get(i));
            int value2 = getCardValue(hand2.get(i));
            if (value1 != value2) {
                return Integer.compare(value1, value2);
            }
        }
        return 0;
    }

    private static int compareFourOfAKind(List<Card> hand1, List<Card> hand2) {
        return compareGroupsOfCards(hand1, hand2, 4);
    }

    private static int compareFullHouse(List<Card> hand1, List<Card> hand2) {
        return compareGroupsOfCards(hand1, hand2, 3);
    }

    private static int compareThreeOfAKind(List<Card> hand1, List<Card> hand2) {
        return compareGroupsOfCards(hand1, hand2, 3);
    }

    private static int compareTwoPair(List<Card> hand1, List<Card> hand2) {
        return compareGroupsOfCards(hand1, hand2, 2);
    }

    private static int compareGroupsOfCards(List<Card> hand1, List<Card> hand2, int groupSize) {
        Map<Integer, Integer> counts1 = countCardValues(hand1);
        Map<Integer, Integer> counts2 = countCardValues(hand2);

        int highest1 = findHighestGroup(counts1, groupSize);
        int highest2 = findHighestGroup(counts2, groupSize);

        if (highest1 != highest2) {
            return Integer.compare(highest1, highest2);
        }

        List<Integer> remaining1 = findRemainingCards(counts1, groupSize);
        List<Integer> remaining2 = findRemainingCards(counts2, groupSize);

        for (int i = 0; i < Math.min(remaining1.size(), remaining2.size()); i++) {
            int comp = Integer.compare(remaining1.get(i), remaining2.get(i));
            if (comp != 0) {
                return comp;
            }
        }

        return 0;
    }

    private static Map<Integer, Integer> countCardValues(List<Card> hand) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (Card card : hand) {
            int value = getCardValue(card);
            counts.put(value, counts.getOrDefault(value, 0) + 1);
        }
        return counts;
    }

    private static int findHighestGroup(Map<Integer, Integer> counts, int groupSize) {
        return counts.entrySet().stream()
                .filter(entry -> entry.getValue() == groupSize)
                .map(Map.Entry::getKey)
                .max(Integer::compareTo)
                .orElse(0);
    }

    private static List<Integer> findRemainingCards(Map<Integer, Integer> counts, int groupSize) {
        List<Integer> remaining = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() < groupSize) {
                for (int i = 0; i < entry.getValue(); i++) {
                    remaining.add(entry.getKey());
                }
            }
        }
        Collections.sort(remaining, Collections.reverseOrder());
        return remaining;
    }

    private static boolean isStraightFlush(List<Card> cards) {
        return isFlush(cards) && isStraight(cards);
    }

    private static boolean isFlush(List<Card> cards) {
        Suit suit = cards.get(0).cardSuit;
        return cards.stream().allMatch(card -> card.cardSuit == suit);
    }

    private static boolean isStraight(List<Card> cards) {
        Set<Integer> values = new HashSet<>(cards.stream().map(CombinationResolver::getCardValue).toList());
        List<Integer> sortedValues = new ArrayList<>(values);
        Collections.sort(sortedValues);

        if (sortedValues.size() != 5) return false;
        return sortedValues.get(4) - sortedValues.get(0) == 4;
    }

    private static boolean isFourOfAKind(List<Card> cards) {
        return hasGroup(cards, 4);
    }

    private static boolean isFullHouse(List<Card> cards) {
        Map<Integer, Integer> counts = countCardValues(cards);
        return counts.values().contains(3) && counts.values().contains(2);
    }

    private static boolean isThreeOfAKind(List<Card> cards) {
        return hasGroup(cards, 3);
    }

    private static boolean isTwoPair(List<Card> cards) {
        Map<Integer, Integer> counts = countCardValues(cards);
        return Collections.frequency(counts.values(), 2) == 2;
    }

    private static boolean hasGroup(List<Card> cards, int groupSize) {
        return countCardValues(cards).values().contains(groupSize);
    }

    private static int getCardValue(Card card) {
        Map<String, Integer> cardValueMap = new HashMap<>();
        cardValueMap.put("A", 14);
        cardValueMap.put("K", 13);
        cardValueMap.put("Q", 12);
        cardValueMap.put("J", 11);
        cardValueMap.put("10", 10);
        cardValueMap.put("9", 9);
        cardValueMap.put("8", 8);
        cardValueMap.put("7", 7);
        cardValueMap.put("6", 6);
        cardValueMap.put("5", 5);
        cardValueMap.put("4", 4);
        cardValueMap.put("3", 3);
        cardValueMap.put("2", 2);
        cardValueMap.put("1", 1);

        return cardValueMap.getOrDefault(card.cardName, 0);
    }
}