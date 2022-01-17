//
//  FeaturedListItem.swift
//  iosApp
//
//  Created by Harry on 2022/01/16.
//  Copyright © 2022 orgName. All rights reserved.
//

import SwiftUI
import presentation

struct FeaturedListItem: View {
    
    var featured : DomainFeatured
    
    var body: some View {
        ZStack {
            
            VStack {
                Text(featured.title).bold().font(.headline)
                Text(featured.description_).bold().font(.subheadline)
            }.frame(height: 300, alignment: .bottom).padding(10)
            
        }
    }
}

//struct FeaturedListItem_Previews: PreviewProvider {
//    static var previews: some View {
//        FeaturedListItem()
//    }
//}
