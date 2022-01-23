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
        
            ZStack(alignment:.bottomLeading) {
                
                Rectangle()
                    .fill(Color.black)
                    .frame(maxWidth : 380, maxHeight : 450)
                    .cornerRadius(20)
                
                AsyncImage(url: URL(string: featured.photoUrl)){ image in
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                        .scaledToFill()
                        .frame(maxWidth : 380, maxHeight : 450)
                        .clipped()
                } placeholder: {
                    ProgressView()
                }
                .opacity(0.5)
                .cornerRadius(20)
                
                VStack(alignment: .leading, spacing: 15) {
                    
                    Text(featured.title).bold().font(.title)
                        .foregroundColor(.white)
                        .multilineTextAlignment(.leading)
                    
                    Text(featured.description_).bold().font(.headline)
                        .foregroundColor(.white)
                        .multilineTextAlignment(.leading)
                    
                }.padding(24).frame(maxWidth: .infinity,
                                    alignment: .leading)
                
            }.frame(width:380, height:450, alignment: .bottomLeading)
            .shadow(radius: 10)
        
    }
}

struct FeaturedListItem_Previews: PreviewProvider {
    static var previews: some View {
        FeaturedListItem(featured: FakeFeatured())
    }
}

class FakeFeatured : DomainFeatured {
    
    
    var description_: String = "날씨가 추워지면서 실외 데이트가 어려워지고..."
    
    var id: Int64 = 0
    
    var photoUrl: String = "https://picsum.photos/200/300"
    
    var title: String = "실내 데이트 코스 14가지 추천"
    
    
    
    
}
