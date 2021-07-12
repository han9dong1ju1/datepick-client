//
//  PlacesClientWrapper.swift
//  iosApp
//
//  Created by 박현기 on 2021/07/05.
//  Copyright © 2021 orgName. All rights reserved.
//

import Foundation
import GooglePlaces

let availableFields = GMSPlaceField(
    rawValue: UInt(GMSPlaceField.placeID.rawValue) | UInt(GMSPlaceField.name.rawValue) | UInt(GMSPlaceField.formattedAddress.rawValue) | UInt(GMSPlaceField.coordinate.rawValue) | UInt(GMSPlaceField.businessStatus.rawValue) | UInt(GMSPlaceField.photos.rawValue) | UInt(GMSPlaceField.types.rawValue)
)!


