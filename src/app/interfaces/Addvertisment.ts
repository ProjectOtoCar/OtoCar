import {Image} from './Image';
export interface Addvertisment {
    id?: number;
    price: number;
    dateAdd: Date;
    active: boolean;
    title: string;
    content: string;
    seller: {
        id: number
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
    };
    engine: number;
    enginePower: number;
    model: {
        id: number
    };
    fuel: string;
    mileage: number;
    typeCar: string;
    color: string;
    firstRegistartion: number;
}
