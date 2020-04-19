import {Image} from './Image';
import { Seller } from './Seller';
export interface Addvertisment {
    id?: number;
    price: number;
    dateAdd: Date;
    active: boolean;
    title: string;
    content: string;
    seller: Seller;
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
