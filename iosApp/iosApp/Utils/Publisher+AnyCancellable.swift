//
//  Publisher+.swift
//  iosApp
//
//  Created by Harry on 2022/01/17.
//  Copyright © 2022 orgName. All rights reserved.
//

import Combine

extension Publisher where Failure == Never {
    func weakAssign<T: AnyObject>(
        to keyPath: ReferenceWritableKeyPath<T, Output>,
        on object: T
    ) -> AnyCancellable {
        sink { _ in
        } receiveValue: { [weak object] value in
            object?[keyPath: keyPath] = value
        }
    }
}
