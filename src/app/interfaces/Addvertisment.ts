import {Image} from './Image';
export interface Addvertisment {
    id?: number;
    price: number;
    dateAdd: Date;
    active: boolean;
    title: string;
    content: string;
    seller: {
        id: number,
        firstName?: string;
        lastName?: string;
        phoneNumber?: string;
        isPremium?: boolean;
        premiumAccount?: Date;
    };
    city: {
        id: number
    };
    car: Car;
    images: [Image];
}

interface Car {
    id?: number;
    brand: {
        id: number;
        name?: string
    };
    engine: number;
    enginePower: number;
    model: {
        id: number,
        name?: string
    };
    fuel: string;
    mileage: number;
    typeCar: string;
    color: string;
    firstRegistartion: number;
}
