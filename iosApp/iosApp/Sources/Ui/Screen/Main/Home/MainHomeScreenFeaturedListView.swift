//
//  HomeFeaturedListView.swift
//  iosApp
//
//  Created by Harry on 2022/01/24.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import presentation

struct MainHomeScreenFeaturedListView: View {

    var featuredList: [DomainFeatured]

    @State var isTap = false

    var body: some View {
        HStack(alignment: .center, spacing: 10) {
            ForEach(featuredList, id: \.id) { featured in
                NavigationLink(destination: FeaturedDetailScreenView()) {
                    FeaturedListItem(featured: featured)
                }
            }
        }
                .modifier(ScrollingHStackModifier(items: featuredList.count, itemWidth: UIDevice.isIPad ? 500 : 380, itemSpacing: 10))
                .listRowInsets(EdgeInsets())
                .frame(height: 380, alignment: .center)
                .eraseToAnyView()
    }

    #if DEBUG
    @ObservedObject var iO = injectionObserver
    #endif
}
