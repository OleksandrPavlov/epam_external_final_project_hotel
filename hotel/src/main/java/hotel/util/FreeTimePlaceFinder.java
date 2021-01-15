package hotel.util;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FreeTimePlaceFinder {
    private final DateRange searchField;
    private List<DateRange> rangeList;

    private static final Comparator<DateRange> DATE_RANGE_COMPARATOR = (range1, range2) -> {
        return range1.getBegin().compareTo(range2.getBegin());
    };

    public FreeTimePlaceFinder(DateRange searchField) {
        this.searchField = searchField;
    }

    // this method will return false value if list==0 or list.size()==0;
    // Or at most one of element does not located in searchFild
    // And will return true if list was successfully set

    public boolean setInnerRangeList(List<DateRange> dateRangeList) {
        if (dateRangesValid(dateRangeList)) {
            this.rangeList = dateRangeList;
            return true;
        }
        return false;
    }

    private boolean dateRangesValid(List<DateRange> dateRange) {
        if (dateRange == null || dateRange.size() == 0) {
            return false;
        }
        // check if all ranges is located in field of searchRange
        if (!isInnerFields(dateRange)) {
            return false;
        }
        // check if no one range does not intersecting other
        return !isThereOverlappingRanges(dateRange);
    }

    public static boolean isIntersectingField(DateRange innerField, DateRange externalRange) {
        int v1b = innerField.getEnd().compareTo(externalRange.getBegin());
        int v1e = innerField.getEnd().compareTo(externalRange.getEnd());

        int v2e = innerField.getBegin().compareTo(externalRange.getEnd());
        int v2b = innerField.getBegin().compareTo(externalRange.getBegin());

        return (v1b == 1 && v1e == -1) || (v2e == -1 && v2b == 1);
    }

    public static boolean isInnerField(DateRange innerField, DateRange externalRange) {
        int bCoincidence = innerField.getBegin().compareTo(externalRange.getBegin());
        int eCoincidence = innerField.getEnd().compareTo(externalRange.getEnd());

        return (bCoincidence == 0 || bCoincidence == 1) && (eCoincidence == 0 || eCoincidence == -1);
    }

    private boolean isInnerFields(List<DateRange> dateRangeList) {
        for (DateRange curRange : dateRangeList) {
            if (!isIntersectingField(curRange, searchField)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isOverlapRanges(DateRange dateRange1, DateRange dateRange2) {
        if (isIntersectingField(dateRange1, dateRange2) || isIntersectingField(dateRange2, dateRange1)) {
            return true;
        }
        if (isInnerPoint(dateRange1.getEnd(), dateRange2) || isInnerPoint(dateRange2.getEnd(), dateRange1)) {
            return dateRange1.getEnd().compareTo(dateRange2.getBegin()) != 0
                    && dateRange2.getEnd().compareTo(dateRange1.getBegin()) != 0;
        }
        return false;
    }

    public static boolean isInnerPoint(Date point, DateRange externalRange) {
        int pointBCoincidence = point.compareTo(externalRange.getBegin());
        int pointECoincidence = point.compareTo(externalRange.getEnd());
        return (pointBCoincidence == 0 || pointBCoincidence == 1) && (pointECoincidence == 0 || pointECoincidence == -1);
    }

    public static boolean isThereOverlappingRanges(List<DateRange> dateRangeList) {
        for (DateRange dateRange1 : dateRangeList) {
            for (DateRange dateRange2 : dateRangeList) {
                if (dateRange1 == dateRange2) {
                    continue;
                }
                if (isOverlapRanges(dateRange1, dateRange2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<DateRange> getAllFreeRanges() {
        if (searchField == null || rangeList == null) {
            return null;
        }

        // First we must sort all inner ranges
        rangeList.sort(DATE_RANGE_COMPARATOR);

        // Finding free places
        List<DateRange> freeRanges = new ArrayList<>();
        // checking if range begins from start of searchRange
        if (!isInnerPoint(searchField.getBegin(), rangeList.get(0))) {
            freeRanges.add(new DateRange(searchField.getBegin(), rangeList.get(0).getBegin()));
        }
        for (int i = 0; i < rangeList.size() - 1; i++) {
            Date firstPoint = rangeList.get(i).getEnd();
            Date secondPoint = rangeList.get(i + 1).getBegin();
            if (!(firstPoint.compareTo(secondPoint) == 0)) {
                freeRanges.add(new DateRange(firstPoint, secondPoint));
            }
        }

        if (!isInnerPoint(searchField.getEnd(), rangeList.get(rangeList.size() - 1))) {
            freeRanges.add(new DateRange(rangeList.get(rangeList.size() - 1).getEnd(), searchField.getEnd()));
        }

        return freeRanges.size() > 0 ? freeRanges : null;
    }

}
