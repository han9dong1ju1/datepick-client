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
                    .frame(maxWidth : UIDevice.isIPad ? 500 : 380, maxHeight : 380)
                    .cornerRadius(20)

                AsyncImage(url: URL(string: featured.photoUrl)){ image in
                    image.resizable()
                        .aspectRatio(contentMode: .fit)
                        .scaledToFill()
                        .frame(maxWidth : UIDevice.isIPad ? 500 : 380, maxHeight : 380)
                        .clipped()
                } placeholder: {
                    ProgressView()
                        .frame(maxWidth : UIDevice.isIPad ? 500 : 380, maxHeight : 380)
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

            }.frame(width:UIDevice.isIPad ? 500 : 380, height:380, alignment: .bottomLeading)
            .shadow(radius: 10)

    }
}
