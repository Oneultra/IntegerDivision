package org.task.pavel.provider;

import org.task.pavel.domain.DivisionItem;
import org.task.pavel.domain.DivisionStep;

import java.util.List;

public interface DivisionViewProvider {
    String provideView(DivisionItem divisionItem, List<DivisionStep> steps);
}
