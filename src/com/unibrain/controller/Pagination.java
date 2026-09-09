package com.unibrain.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pagination {
	public final int NO_OF_RECORDS = 4;
	public final int NO_OF_PAGES_SHOWN = 4;

	public Map<String, Object> defaultMethod(int currentPage, Long totalCount) {
		
		Integer lastSeriesBegin = lastSeriesBegin(totalCount);
		Integer lastSeriesEnd = lastSeriesEnd(totalCount);
		Boolean isFirstSeries = isFirstSeries(currentPage);
		Boolean isLastSeries = isLastSeries(currentPage, lastSeriesBegin, lastSeriesEnd);
		
		Integer nextSeriesLinkPage = null;
		Integer previousSeriesLinkPage = null;
		
		if (!isLastSeries) {
			nextSeriesLinkPage = nextSeriesLinkPage(currentPage);
		}
		if (!isFirstSeries) {
			previousSeriesLinkPage = previousSeriesLinkPage(currentPage);
		}

		List<Integer> Pages = Pages(currentPage, totalCount);
		Map<String, Object> paginationMap = new HashMap<>();
		paginationMap.put("isFirstSeries", isFirstSeries);
		paginationMap.put("isLastSeries", isLastSeries);
		paginationMap.put("lastSeriesEnd", lastSeriesEnd);
		paginationMap.put("nextSeriesLinkPage", nextSeriesLinkPage);
		paginationMap.put("previousSeriesLinkPage", previousSeriesLinkPage);
		paginationMap.put("pages", Pages);
		paginationMap.put("currentPage", currentPage);

		return paginationMap;
	}

	public boolean isFirstSeries(int currentPage) {
		if (currentPage >= 1 && currentPage <= NO_OF_PAGES_SHOWN) {
			return true;
		} else {
			return false;
		}

	}

	public boolean isLastSeries(int currentPage, int lastSeriesBegin, int lastSeriesEnd) {
		if (currentPage >= lastSeriesBegin && currentPage <= lastSeriesEnd) {
			return true;
		} else {
			return false;
		}
	}



	
	  public int lastSeriesBegin(Long totalCount) {
		  
		  if(totalCount%(NO_OF_PAGES_SHOWN * NO_OF_RECORDS) ==0) {
			  
		 System.err.println("VALUE "+(int) ((((totalCount-1)/(NO_OF_PAGES_SHOWN *NO_OF_RECORDS))*NO_OF_PAGES_SHOWN)+1)); 
		return (int)((((totalCount-1)/(NO_OF_PAGES_SHOWN * NO_OF_RECORDS))*NO_OF_PAGES_SHOWN)+1);
		  }

		  else {
			  return (int) (((totalCount/(NO_OF_PAGES_SHOWN *  NO_OF_RECORDS))*NO_OF_PAGES_SHOWN)+1);
		  }
	  }	  
	  
	  
	 
	public int lastSeriesEnd(Long totalCount) {
		System.out.println(totalCount + "tc");
		if (totalCount % NO_OF_RECORDS == 0) {
			return (int) (((totalCount - 1) / NO_OF_RECORDS) + 1);
		} else {
			return (int) ((totalCount / NO_OF_RECORDS) + 1);
		}

	}

	public int previousSeriesLinkPage(int currentPage) {
		if (currentPage % NO_OF_PAGES_SHOWN == 0) {
			return (((((currentPage - 1) / NO_OF_PAGES_SHOWN) * NO_OF_PAGES_SHOWN) - NO_OF_PAGES_SHOWN) + 1);
		}
		{

			return ((((currentPage / NO_OF_PAGES_SHOWN) * NO_OF_PAGES_SHOWN) - NO_OF_PAGES_SHOWN) + 1);

		}

	}

	public int nextSeriesLinkPage(int currentPage) {

		if (currentPage % NO_OF_PAGES_SHOWN == 0) {

			return (((((currentPage - 1) / NO_OF_PAGES_SHOWN) * NO_OF_PAGES_SHOWN) + NO_OF_PAGES_SHOWN) + 1);
		}
		{

			return ((((currentPage / NO_OF_PAGES_SHOWN) * NO_OF_PAGES_SHOWN) + NO_OF_PAGES_SHOWN) + 1);

		}

	}

	public int currentSeriesBeginning(int currentPage) {
		if (currentPage % NO_OF_PAGES_SHOWN == 0) {
			return (((currentPage - 1) / NO_OF_PAGES_SHOWN) * NO_OF_PAGES_SHOWN + 1);
		}
		{

			return (((currentPage) / NO_OF_PAGES_SHOWN) * NO_OF_PAGES_SHOWN + 1);

		}

	}

	public List<Integer> Pages(int currentPage, Long totalCount) {
		List<Integer> pages = new ArrayList<Integer>();
		int templastPage = lastSeriesEnd(totalCount);
		System.err.println("TRMP" + templastPage);
		Integer currentSeriesBegin = currentSeriesBeginning(currentPage);
		int pageCount = 0;

		for (int i = 0; i < NO_OF_PAGES_SHOWN; ++i) {
			pageCount = currentSeriesBegin + i;
			pages.add(pageCount);
			if (pageCount == templastPage) {
				break;
			}
		}
		System.err.println("Pgesa" + pages);
		return pages;
	}

}
