//
//  HomeFeaturedPagerView.swift
//  iosApp
//
//  Created by Harry on 2022/01/17.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import presentation
import SwiftUIPager

struct HomeFeaturedPagerView: View {
    
    @StateObject var page: Page = .first()
    
    var featuredList : [DomainFeatured]
    
    var body: some View {
        Pager(
            page: self.page,
            data: featuredList.indices,
            id: \.self
        ) { index in
            FeaturedListItem(featured: featuredList[index])
        }.frame(height : 300)
    }
}

struct HomeFeaturedPagerView_Previews: PreviewProvider {
    static var previews: some View {
        HomeFeaturedPagerView(featuredList: [])
    }
}
