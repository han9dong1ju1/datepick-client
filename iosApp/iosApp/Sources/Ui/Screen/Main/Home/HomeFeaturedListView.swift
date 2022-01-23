//
//  HomeFeaturedListView.swift
//  iosApp
//
//  Created by Harry on 2022/01/24.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import presentation

struct HomeFeaturedListView: View {
    
    var featuredList : [DomainFeatured]
    
    var body: some View {
        
        HStack(alignment:.center, spacing: 10) {
            ForEach(featuredList, id: \.id) { featured in
                FeaturedListItem(featured: featured)
            }
        }.modifier(
            ScrollingHStackModifier(items: featuredList.count, itemWidth: 380, itemSpacing: 10)
        ).listRowInsets(EdgeInsets())
            .frame(height:450, alignment: .center)
    }
}


struct HomeFeaturedListView_Previews: PreviewProvider {
    static var previews: some View {
        HomeFeaturedListView(
        featuredList: [
        FakeFeatured(), FakeFeatured(), FakeFeatured(), FakeFeatured()
        ])
    }
}
